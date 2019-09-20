 SELECT trainnumber FROM route
where stationid in
(
select stationid from station
           where stationcode in (?, ?)
)