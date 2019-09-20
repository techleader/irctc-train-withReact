select stationname from route as r
 inner join station s on s.stationid =r.stationid
 where trainnumber=?