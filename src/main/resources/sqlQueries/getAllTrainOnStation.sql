SELECT * FROM Trains
where Trainnumber in
(
SELECT Trainnumber FROM STATION
INNER JOIN route ON route.stationid = station.stationid
WHERE stationcode=?
)