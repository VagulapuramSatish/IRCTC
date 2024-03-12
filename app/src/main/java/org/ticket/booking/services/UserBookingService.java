package org.ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticket.booking.entities.User;
import org.ticket.booking.utilities.UserServiceUtil;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH= "/Applications/java projects/IRCTC/app/src/main/java/org/ticket/booking/localDb/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user= user1;
        File users=new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});

        public Boolean loginUser(){
            Optional<User> foundUser = userList.stream().filter(user -> {
                return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
            }).findFirst();
            return foundUser.isPresent();
        }

        public Boolean signUp(User user){
            try{
                userList.add(user);
                saveUserListToFile();
                return Boolean.TRUE;
            }catch (IOException ex){
                return Boolean.FALSE;
            }
        }

        private void saveUserListToFile() throws IOException {
            File usersFile = new File(USERS_PATH);
            objectMapper.writeValue(usersFile, userList);
        }


        public void fetchBookings(){
            Optional<User> userFetched = userList.stream().filter(user1 -> {
                return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
            }).findFirst();
            if(userFetched.isPresent()){
                userFetched.get().printTickets();
            }
        }

        public Boolean cancelBooking(String ticketId){
            // todo: Complete this function
            return Boolean.FALSE;
        }

        public List<Train> getTrains(String source, String destination){
            try{
                TrainService trainService = new TrainService();
                return trainService.searchTrains(source, destination);
            }catch(IOException ex){
                return new ArrayList<>();
            }
        }

        public List<List<Integer>> fetchSeats(Train train){
            return train.getSeats();
        }

        public Boolean bookTrainSeat(Train train, int row, int seat) {
            try{
                TrainService trainService = new TrainService();
                List<List<Integer>> seats = train.getSeats();
                if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                    if (seats.get(row).get(seat) == 0) {
                        seats.get(row).set(seat, 1);
                        train.setSeats(seats);
                        trainService.addTrain(train);
                        return true; // Booking successful
                    } else {
                        return false; // Seat is already booked
                    }
                } else {
                    return false; // Invalid row or seat index
                }
            }catch (IOException ex){
                return Boolean.FALSE;
            }
        }


    }

}
