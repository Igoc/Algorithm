import java.util.HashMap;
import java.util.Map;

class Solution {
    public long assignRoom(long roomNumber, Map<Long, Long> rooms) {
        if (!rooms.containsKey(roomNumber)) {
            rooms.put(roomNumber, roomNumber);

            return roomNumber;
        }

        rooms.replace(roomNumber, assignRoom(rooms.get(roomNumber) + 1, rooms));

        return rooms.get(roomNumber);
    }

    public long[] solution(long k, long[] room_number) {
        Map<Long, Long> rooms = new HashMap<>();

        for (int index = 0; index < room_number.length; index++) {
            room_number[index] = assignRoom(room_number[index], rooms);
        }

        return room_number;
    }
}