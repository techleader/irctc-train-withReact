package com.train.controller;

import com.train.repository.TrainJpaRepository;
import com.train.model.Train;
import com.train.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainController {

    @Autowired
    DataService dataService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("train/all")
    public List<Train> getAllTrain() {
        return dataService.getAllTrain();

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "train/saveNewTrain", method = RequestMethod.POST)
    public List<Train> SaveNewTrain(@RequestParam("trainNum") int num, @RequestParam("trainName") String name,
                                    @RequestParam("start") String sst, @RequestParam("end") String end, @RequestParam("route") String[] route) {
        Train train = new Train(num, name, sst, end, route);
        dataService.saveNewTrain(train);
        return dataService.getAllTrain();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "train/getTrainBetweenStations", method = RequestMethod.GET)
    @ResponseBody
    public List<Train> getTrainBetweenStation(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) {

        return dataService.getTrainBetweenStation(from, to);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "train/getAllTrainOnStation", method = RequestMethod.GET)
    @ResponseBody
    public List getAllTrainOnStation(@RequestParam(value = "station") String station) {

        return dataService.getAllTrainOnStation(station);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "train/getRouteOfTrain", method = RequestMethod.GET)
    @ResponseBody
    public String[] getRouteOfTrain(@RequestParam(value = "trainNumber") int trainNum) {
        return dataService.getRouteOfTrain(trainNum);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "train/getTrain")
    @ResponseBody
    public Train getTrain(@RequestParam(value = "trainNum") int trainNum) {

        return dataService.getTrain(trainNum);
    }

    @RequestMapping(value = "train/updateTrain")
    @ResponseBody
    public void updateTrain(@RequestParam("trainNum") int num, @RequestParam("trainName") String name,
                            @RequestParam("start") String sst, @RequestParam("end") String end) {
        Train train = new Train(num, name, sst, end, null);
        dataService.updateTrain(train);
    }

    @RequestMapping(value = "train/setRoute")
    @ResponseBody
    public void setRoute(@RequestParam("route") String route[], @RequestParam("trainNum") int trainNum) {

        dataService.setRoute(route, trainNum);
    }
}