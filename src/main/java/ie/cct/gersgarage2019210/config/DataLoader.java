package ie.cct.gersgarage2019210.config;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ie.cct.gersgarage2019210.model.BookingStatus;
import ie.cct.gersgarage2019210.model.BookingType;
import ie.cct.gersgarage2019210.model.Item;
import ie.cct.gersgarage2019210.model.ServiceType;
import ie.cct.gersgarage2019210.model.User;
import ie.cct.gersgarage2019210.model.VehicleEngine;
import ie.cct.gersgarage2019210.model.VehicleMake;
import ie.cct.gersgarage2019210.model.VehicleModel;
import ie.cct.gersgarage2019210.model.VehicleType;
import ie.cct.gersgarage2019210.repository.BookingStatusRepository;
import ie.cct.gersgarage2019210.repository.BookingTypeRepository;
import ie.cct.gersgarage2019210.repository.ItemRepository;
import ie.cct.gersgarage2019210.repository.ServiceTypeRepository;
import ie.cct.gersgarage2019210.repository.UserRepository;
import ie.cct.gersgarage2019210.repository.VehicleEngineRepository;
import ie.cct.gersgarage2019210.repository.VehicleMakeRepository;
import ie.cct.gersgarage2019210.repository.VehicleModelRepository;
import ie.cct.gersgarage2019210.repository.VehicleTypeRepository;

//https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	@Autowired
	private VehicleEngineRepository vehicleEngineRepository;
	@Autowired
	private BookingStatusRepository bookingStatusRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ServiceTypeRepository serviceTypeRepository;
	@Autowired
	private VehicleMakeRepository vehicleMakeRepository;
	@Autowired
	private VehicleModelRepository vehicleModelRepository;
	@Autowired
	private BookingTypeRepository bookingTypeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
    	Optional<User> user = userRepository.findByName("admin");
    	System.out.println(user);
    	if(!user.isPresent()) {
    		generateData();
    	}
    }
    
    public void generateData() {
    	generateUsers();
        generateBookingType();
        generateVehicleType();
        generateVehicleEngine();
        generateBookingStatus();
        generateItems();   
        generateServices();
        generateVehicleMakesAndModels();
    }

	private void generateVehicleMakesAndModels() {
		VehicleMake make = new VehicleMake(null, "Audi", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        VehicleModel model = new VehicleModel(null, "A4", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "A6", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Q7", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "BMW", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Z3", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "7 series", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Citroen", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "C4", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Saxo", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Fiat", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Punto", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Scudo", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Ford", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Fiesta", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Focus", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Honda", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Civic", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Jazz", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Renault", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Clio", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Megane", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Volkswagen", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Golf", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Passat", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        //https://motorcycleviews.com/
        make = new VehicleMake(null, "Yamaha", vehicleTypeRepository.findByName("Motorbike").get());
        make = vehicleMakeRepository.save(make);
        vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "950 Touring", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "FZ07", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Kawasaki", vehicleTypeRepository.findByName("Motorbike").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Vulcan 900", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Ninja 1000", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Suzuki", vehicleTypeRepository.findByName("Motorbike").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Intruder M1800R", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "SV650S", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        //https://www.carmax.com/articles/best-minivans
        make = new VehicleMake(null, "Honda", vehicleTypeRepository.findByName("Van").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Odyssey", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Nissan", vehicleTypeRepository.findByName("Van").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Guest", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Kia", vehicleTypeRepository.findByName("Van").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Sedona", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Peugeout", vehicleTypeRepository.findByName("Bus").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Boxer 435", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Boxer 335", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Citroen", vehicleTypeRepository.findByName("Bus").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Relay 35", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        
        make = new VehicleMake(null, "Fiat", vehicleTypeRepository.findByName("Bus").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Ducato 35", make);
        vehicleModelRepository.save(model);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);   
        
        make = new VehicleMake(null, "Other", vehicleTypeRepository.findByName("Car").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        make = new VehicleMake(null, "Other", vehicleTypeRepository.findByName("Motorbike").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        make = new VehicleMake(null, "Other", vehicleTypeRepository.findByName("Van").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
        make = new VehicleMake(null, "Other", vehicleTypeRepository.findByName("Bus").get());
        make = vehicleMakeRepository.save(make);
        model = new VehicleModel(null, "Other", make);
        vehicleModelRepository.save(model);
	}

	private void generateServices() {
		ServiceType service = new ServiceType(null,"Computer Diagnostics" , new BigDecimal(50));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Lube/oil/filter change" , new BigDecimal(20));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Radiator Flush" , new BigDecimal(35));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Transmission Fluid" , new BigDecimal(25));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"A/C Recharge & Diagnostic" , new BigDecimal(60));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Timing Belt Replacement" , new BigDecimal(50));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Tire rotation and balance" , new BigDecimal(60));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Battery replacement" , new BigDecimal(50));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Anti-Lock system diagnosis" , new BigDecimal(80));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Suspension system" , new BigDecimal(200));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Alignments" , new BigDecimal(60));
        serviceTypeRepository.save(service);
        service = new ServiceType(null,"Shock and Strut Replacement" , new BigDecimal(50));
        serviceTypeRepository.save(service);
	}

	private void generateItems() {
		//https://www.mister-auto.ie/car-parts/
        Item item = new Item(null,"Front Brake Pad Set" , new BigDecimal(21.5));
        itemRepository.save(item);
        item = new Item(null,"Rear Brake Pad Set", new BigDecimal(25));
        itemRepository.save(item);
        item = new Item(null, "Oil Filter", new BigDecimal(4.8));
        itemRepository.save(item);
        item = new Item(null, "Engine Oil - 5L", new BigDecimal(45.6));
        itemRepository.save(item);
        item = new Item(null, "Engine Oil - 5L", new BigDecimal(7.1));
        itemRepository.save(item);
        item = new Item(null, "Cabin Air Filter" , new BigDecimal(6));
        itemRepository.save(item);
        item = new Item(null,"Fuel Filter" , new BigDecimal(16.2));
        itemRepository.save(item);
        item = new Item(null, "Shock Absorber", new BigDecimal(23.9));
        itemRepository.save(item);
        item = new Item(null,"Coil Spring" , new BigDecimal(11.9));
        itemRepository.save(item);
        item = new Item(null,"Link Stabiliser" , new BigDecimal(14.1));
        itemRepository.save(item);
        item = new Item(null,"Suspension Strut Support Bearing" , new BigDecimal(17.4));
        itemRepository.save(item);
        item = new Item(null,"Wheel Bearing Kit" , new BigDecimal(14.6));
        itemRepository.save(item);
        item = new Item(null,"Wheel Hub" , new BigDecimal(49.3));
        itemRepository.save(item);
        item = new Item(null,"Track Control Arm" , new BigDecimal(47));
        itemRepository.save(item);
        item = new Item(null,"Bulb Indicator" , new BigDecimal(2.2));
        itemRepository.save(item);
        item = new Item(null,"Wiper Blade" , new BigDecimal(8.79));
        itemRepository.save(item);
        item = new Item(null,"Starter" , new BigDecimal(105.90));
        itemRepository.save(item);
        item = new Item(null,"End Silencer" , new BigDecimal(68.9));
        itemRepository.save(item);
        item = new Item(null,"Catalytic Converter" , new BigDecimal(220.9));
        itemRepository.save(item);
        item = new Item(null, "Alternator", new BigDecimal(206.9));
        itemRepository.save(item);
        item = new Item(null, "Battery Charger", new BigDecimal(97.9));
        itemRepository.save(item);
        item = new Item(null, "Injector Nozzle", new BigDecimal(427.9));
        itemRepository.save(item);
        item = new Item(null,"Brake Drum" , new BigDecimal(78.9));
        itemRepository.save(item);
        item = new Item(null,"Compressor, Air Conditioning" , new BigDecimal(318.9));
        itemRepository.save(item);
        item = new Item(null,"Interior Blower" , new BigDecimal(119.9));
        itemRepository.save(item);
        item = new Item(null,"Gas Spring" , new BigDecimal(21.09));
        itemRepository.save(item);
        item = new Item(null,"Radiator Fan" , new BigDecimal(181.9));
        itemRepository.save(item);
        item = new Item(null,"Carrier, Brake Caliper" , new BigDecimal(97.9));
        itemRepository.save(item);
        item = new Item(null,"Thermostat Housing" , new BigDecimal(26.8));
        itemRepository.save(item);
        item = new Item(null, "Drive Shafts", new BigDecimal(96.9));
        itemRepository.save(item);
        item = new Item(null, "Exhaust Pipe", new BigDecimal(48.5));
        itemRepository.save(item);
        item = new Item(null, "Switch, window winder" , new BigDecimal(33));
        itemRepository.save(item);
	}

	private void generateBookingStatus() {
		BookingStatus booked = new BookingStatus(null, "Booked");
        bookingStatusRepository.save(booked);
        BookingStatus inService = new BookingStatus(null, "In Service");
        bookingStatusRepository.save(inService);
        BookingStatus fixed = new BookingStatus(null, "Fixed/ Completed");
        bookingStatusRepository.save(fixed);
        BookingStatus collected = new BookingStatus(null, "Collected");
        bookingStatusRepository.save(collected);
        BookingStatus unrepairable = new BookingStatus(null, "Unrepairable/ Scrapped");
        bookingStatusRepository.save(unrepairable);
	}

	private void generateVehicleEngine() {
		VehicleEngine diesel = new VehicleEngine(null, "Diesel");
        vehicleEngineRepository.save(diesel);
        VehicleEngine petrol = new VehicleEngine(null, "Petrol");
        vehicleEngineRepository.save(petrol);
        VehicleEngine hybrid = new VehicleEngine(null, "Hybrid");
        vehicleEngineRepository.save(hybrid);
        VehicleEngine eletric = new VehicleEngine(null, "Eletric");
        vehicleEngineRepository.save(eletric);
	}

	private void generateVehicleType() {
		VehicleType car = new VehicleType(null, "Car");
        vehicleTypeRepository.save(car);
        VehicleType motorbike = new VehicleType(null, "Motorbike");
        vehicleTypeRepository.save(motorbike);
        VehicleType van = new VehicleType(null, "Van");
        vehicleTypeRepository.save(van);
        VehicleType bus = new VehicleType(null, "Bus");
        vehicleTypeRepository.save(bus);
	}

	private void generateBookingType() {
		BookingType bookingType = new BookingType(null, "Annual Service", new BigDecimal(200), false);
        bookingTypeRepository.save(bookingType);
        bookingType = new BookingType(null, "Major Service", new BigDecimal(250), false);
        bookingTypeRepository.save(bookingType);
        bookingType = new BookingType(null, "Repair/Fault", new BigDecimal(150), false);
        bookingTypeRepository.save(bookingType);
        bookingType = new BookingType(null, "Major Repair", new BigDecimal(300), true);
        bookingTypeRepository.save(bookingType);
	}

	private void generateUsers() {
		User adminUser = new User(null, "admin", "admin", "admin", null, null, false, true, true, "admin");
        userRepository.save(adminUser);
        User mechanic1 = new User(null, "mechanic1", "Samuel", "Murray", "samuelmurray@gersgarage.ie", null, true, false, true, "123");
        userRepository.save(mechanic1);
        User mechanic2 = new User(null, "mechanic2", "Joseph", "Smith", "josephsmith@gersgarage.ie", null, true, false, true, "123");
        userRepository.save(mechanic2);
        User mechanic3 = new User(null, "mechanic3", "Leonard", "Doyle", "leonarddoyle@gersgarage.ie", null, true, false, true, "123");
        userRepository.save(mechanic3);
        User mechanic4 = new User(null, "mechanic4", "Edward", "Mosbi", "edwardmosbi@gersgarage.ie", null, true, false, true, "123");
        userRepository.save(mechanic4);
        User customer = new User(null, "customer", "Scarlet", "Murphy", "scarletmurphy@gersgarage.ie", null, false, false, true, "123");
        userRepository.save(customer);
	}

}