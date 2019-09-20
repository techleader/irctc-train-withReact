select * from Route

insert into employes values('rajesh',10000);
insert into station values('s3','MK','MAKRANA');
insert into station values('s4','BK','BIKANER');
insert into station values('s5','MT','MERTA ROAD');
insert into station values('s6','GO','GOTAN');


1.....................
    CREATE TABLE employes


2......................
    SELECT MAX(salary) as highestSalary
    FROM employes;


3..........................

   SELECT MAX(salary) as highestSalary
    FROM employes where salary NOT IN(SELECT MAX(salary) from employes)



4..............
    select * from employes
    where salary =
    (
        SELECT MAX(salary) as highestSalary
        FROM employes where salary NOT IN(SELECT MAX(salary) from employes)
    )

5.................
        1). count all Stations of Route of any train......

            select count(stationname)
            from
             (
            select stationname from Station
            where stationid in
            (
            select stationcode from route
            where trainnumber=5642
            )
            )

6........ALTER (update coulum query)........

 ALTER TABLE trains
ALTER COLUMN id int NOT NUll AUTO_INCREMENT

7.........Join Query.........
    ..........find route.....
select stationCode from route as
   inner join station s on s.stationid =r.stationid
   where trainnumber=?

.............find  All train from A single Station...........
SELECT * FROM STATION
INNER JOIN route ON route.stationid = station.stationid
WHERE stationcode='JP'