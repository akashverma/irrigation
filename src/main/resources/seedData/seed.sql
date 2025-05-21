CREATE TABLE plot (
    id int,
    water_amount VARCHAR(10),
    length INT,
    is_irrigated BOOLEAN,
    breadth INT,
    time_slot VARCHAR(20)
);

insert into plot values(1,'100',4,false,2,'MORNING');
insert into plot values(2,'150',6,false,3,'NOON');
insert into plot values(3,'200',8,false,4,'EVENING');