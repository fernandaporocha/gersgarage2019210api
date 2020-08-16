package ie.cct.gersgarage2019210.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.BookingDTO;
import ie.cct.gersgarage2019210.model.Booking;
import ie.cct.gersgarage2019210.model.BookingItem;
import ie.cct.gersgarage2019210.model.BookingType;
import ie.cct.gersgarage2019210.model.ServiceType;
import ie.cct.gersgarage2019210.model.User;
import ie.cct.gersgarage2019210.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private BookingTypeService bookingTypeService;
	@Autowired
	private BookingStatusService bookingStatusService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ServiceTypeService serviceTypeService;
	@Autowired
	private BookingItemsService bookingItemsService;

	public void create(BookingDTO dto) {
		BookingType bookingType = dto.getBookingTypeId()==null?null:bookingTypeService.find(dto.getBookingTypeId());
		System.out.println("create " +dto);
		ArrayList<Integer> serviceIds = dto.getServiceIds()!=null && dto.getServiceIds().length>0?new ArrayList<Integer>(Arrays.asList(dto.getServiceIds())):null;
		if ((getSumRequiredSlotsByBookingDate(dto.getBookingDate())+(bookingType.isDoubledSlot()?2:1))>16) {
			System.out.println("Required slots bigger then 16");
		}else {
			if (dto.getBookingDate()!= null && bookingType!=null) {
				if (dto.getStaffId()==null||staffIsAvailable(userService.find(dto.getStaffId()), dto.getBookingDate(), bookingType.isDoubledSlot()?2:1)){
					Booking booking = new Booking(
							null, 
							dto.getCustomerId()==null?null:userService.find(dto.getCustomerId()) , 
							dto.getStaffId()==null?null:userService.find(dto.getStaffId()), 
							vehicleService.find(dto.getVehicleId()), 
							bookingType, 
							dto.getStatusId()==null?bookingStatusService.findByName("Booked"):bookingStatusService.find(dto.getStatusId()), 
							serviceIds==null?null:serviceTypeService.findAllById(serviceIds), 
							dto.getBookingDate(),
							bookingType.isDoubledSlot()?2:1,
							dto.getComments());
					System.out.println("save" + booking);
					Booking savedBooking = repository.save(booking);
					System.out.println("savedBooking "+savedBooking);
					bookingItemsService.save(dto.getBookingItems(), savedBooking);
				}
				else {
					System.out.println("Mechanic not available");
				}
			}else {
				System.out.println("No date and/or booking type was informed");
			}
		}
	}
	
	private boolean staffIsAvailable(User mechanic, LocalDate bookingDate, int requiredSlots) {
		List<Booking> bookings = repository.findByResponsibleStaffAndDate(mechanic, bookingDate);
		int usedSlots = 0;
		for (Booking booking : bookings) {
			usedSlots += booking.getRequiredSlots();
		}
		System.out.println(usedSlots);
		return (usedSlots+requiredSlots)>4?false:true;
	}

	public List<Booking> findAll(){
		return (List<Booking>) repository.findAll();
	}
	
	public List<Booking> findByCustomer(User user) {
		System.out.println((List<Booking>) repository.findByCustomer(user));
		return (List<Booking>) repository.findByCustomer(user);
	}
	
	public List<Booking> findByMechanic(User user) {
		System.out.println(user);
		System.out.println((List<Booking>) repository.findByResponsibleStaff(user));
		return (List<Booking>) repository.findByResponsibleStaff(user);
	}
	
	public List<Booking> findByDateRange(LocalDate minDate, LocalDate maxDate){
		minDate=minDate.plusDays(1);
		maxDate=maxDate.plusDays(1);
		
		return repository.findByDateBetween(minDate, maxDate);	
	}
	
	public Booking find(Integer id) {
		Optional<Booking> booking = (Optional<Booking>) repository.findById(id);
		return booking.isPresent()?booking.get():null;
	}
	
	public void update(BookingDTO dto) {
		BookingType bookingType = bookingTypeService.find(dto.getBookingTypeId());
		Booking booking = find(dto.getId());
		//If the date was changed, check if there are slots available on the new date
		if(dto.getBookingDate()!=booking.getDate()&&((getSumRequiredSlotsByBookingDate(dto.getBookingDate())+(bookingType.isDoubledSlot()?2:1))>16)) {
			System.out.println("Required slots maior que 16");
		}else {
			//It does not save a the booking without a booking date or booking type
			if (dto.getBookingDate()!= null && dto.getBookingTypeId()!=null) {
				//If there is a responsible mechanic, check if he has an available slot
				if (dto.getStaffId()==null||
						staffIsAvailable(userService.find(dto.getStaffId()), dto.getBookingDate(), bookingType.isDoubledSlot()?2:1)){
					ArrayList<Integer> serviceIds = dto.getServiceIds()==null?null:new ArrayList<Integer>(Arrays.asList(dto.getServiceIds()));
					booking.setResponsibleStaff(dto.getStaffId()==null?null:userService.find(dto.getStaffId()));
					booking.setRequiredBooking(bookingType); 
					booking.setStatus(bookingStatusService.find(dto.getStatusId()));
					booking.setRequiredServices(serviceIds==null?null:serviceTypeService.findAllById(serviceIds));
					booking.setRequiredSlots(bookingType.isDoubledSlot()?2:1);
					booking.setComments(dto.getComments());
					booking.setDate(dto.getBookingDate());
					Booking savedBooking = repository.save(booking);
					System.out.println("savedBooking "+booking);
					bookingItemsService.deleteByBookingId(savedBooking.getId());
					if (dto.getBookingItems()!=null) {
						bookingItemsService.save(dto.getBookingItems(), savedBooking);
					}
				}
			}
		}
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public int getSumRequiredSlotsByBookingDate(LocalDate date) {
		List<Booking> bookings = repository.findByDate(date);
		int requiredSlots = 0;
		for(Booking booking: bookings) {
			requiredSlots += booking.getRequiredSlots();
		}
		System.out.println("requiredSlots  " + requiredSlots);
		return requiredSlots;
	}
	
	public List<BookingItem> getBookingItems(Integer id){
		List<BookingItem> list = bookingItemsService.getItemsByBookingId(id);
		return list;
	}
	
	public BigDecimal calculateTotalService(Booking booking) {
		BigDecimal total = new BigDecimal(0);
		total.add(booking.getRequiredBooking().getPrice());
		
		for (ServiceType service: booking.getRequiredServices()) {
			total.add(service.getPrice());
		}
		
		return total;
	}
	
	public BigDecimal calculateTotal(Integer bookingId) {
		BigDecimal total = new BigDecimal(0);
		Booking booking = find(bookingId);
		List<BookingItem> items = getBookingItems(bookingId);
		total.add(booking.getRequiredBooking().getPrice());
		
		for (ServiceType service: booking.getRequiredServices()) {
			total.add(service.getPrice());
		}
		
		for (BookingItem item : items) {
			total.add(itemService.find(item.getId()).getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		
		return total;
	}
		
	public Resource getResource(HttpServletResponse response) {
		response.setContentType("application/pdf; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
		response.setHeader("filename", "invoice.pdf");
		Resource resource = new FileSystemResource("C:\\temp\\invoice.pdf");
		return resource;
	}

	
}
