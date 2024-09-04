create database swastikpharma;
use swastikpharma;

create table admin (
	username varchar(255),
    password varchar(255)
);
insert into admin values('admin','admin');
select * from admin;

create table chemist (
	chemist_id int not null auto_increment,
    chemist_name varchar(255) not null,
    address varchar(255) not null,
    phoneno int8 unique not null,
    dlno1 varchar(255) unique not null,
    dlno2 varchar(255) unique not null,
    gstin varchar(255) unique not null,
    primary key(chemist_id)
);
insert into chemist (chemist_name, address, phoneno, dlno1, dlno2, gstin) values ('Shree Sadguru Medicals', '3119/2, VTU Road, Tipu Sultan Nagar, Macche, Belagavi - 590014', 8971632695, 'KA-BG-3/20/104152', 'KA-BG-3/20/104153', '29BAYPP4615F2Z3'),
('Orthopaedic Centre Sai Medicals', '235/2, OPP. Fire Brigade Station, Khanapur Road, Belagavi - 590018', 9448230547, 'KA-BGM/20/1193', 'KA-BGM/21/1193', '29BARPS1324R1ZR'),
('Shah Medicals & General Stores', 'CTS 1280, Huddar Building Shop No. 1, Hosa oni, Bailhongal', 987456213, 'KA-BGM/20/1769', 'KA-BGM/21/1769', '29APVPB9225Q1ZW'),
('Vijay Medicals', 'Sambhaji Nagar, Vadagaon, Belagavi', 8845213690, 'KA/BG1/119399', 'KA/BG1/119400', '29ATDPC7392P1ZZ');
select * from chemist;

create table medicine (
	manufacturer varchar(30) not null,
    med_name varchar(255) not null,
    pack int not null,
    hsn int not null,
    batchno varchar(30) unique not null,
    expiry_date varchar(30) not null,
    mrp float not null,
    trp float not null,
    quantity int not null,
    gst int not null,
    primary key(batchno)
);
insert into medicine values ('Agron', 'Agpar-Kid-DT-125', 10, 9099, 'ARK-01', 'MAR-25', 15.80, 11.4, 50, 12),
('Agron', 'Agroben-I-Plus', 1, 9021, 'API-08', 'NOV-26', 33.00, 25.08, 50, 12),
('Agron', 'Agthro-500', 6, 9064, 'TRO-21', 'JAN-26', 158.32, 120.33, 60, 12),
('Agron', 'Cefirag-200', 10, 9099, 'CAA-29', 'JAN-26', 109.42, 83.16, 50, 12),
('Agron', 'Cefpogem-200', 10, 2019, 'CPP-27', 'NOV-25', 210.00, 159.60, 50, 12),
('Agron', 'Doloron-P', 10, 9099, 'ORO-62', 'NOV-26', 30.00, 22.08, 50, 12),
('Agron', 'Etorag-90', 10, 9072, 'ETZ-34', 'NOV-26', 130.00, 98.80, 50, 12),
('Agron', 'Infafen-SP', 1, 9069, 'AFE-33', 'OCT-25', 118.00, 89.68, 50, 12);
select * from medicine;

create table orders (
	billno int not null unique auto_increment,
    date varchar(30) not null,
    name varchar(255) not null,
    totalamt double not null,
    primary key(billno)
);

drop table orders;
select count(billno) from orders;
select * from orders;
