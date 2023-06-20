class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliveryTotalCount = 0;
        int pickupTotalCount = 0;

        for (int index = 0; index < n; index++) {
            deliveryTotalCount += deliveries[index];
            pickupTotalCount += pickups[index];
        }

        int house = n;
        int deliveryCount = 0;
        int pickupCount = 0;

        long answer = 0L;

        while (deliveryCount < deliveryTotalCount || pickupCount < pickupTotalCount) {
            while (deliveries[house - 1] == 0 && pickups[house - 1] == 0) {
                house--;
            }

            answer += house;

            int deliveryCapacity = cap;

            for (int offset = 0; offset < house; offset++) {
                int delivery = Math.min(deliveries[house - offset - 1], deliveryCapacity);

                deliveries[house - offset - 1] -= delivery;
                deliveryCount += delivery;
                deliveryCapacity -= delivery;

                if (deliveryCapacity == 0) {
                    break;
                }
            }

            int pickupCapacity = cap;

            for (int offset = 0; offset < house; offset++) {
                int pickup = Math.min(pickups[house - offset - 1], pickupCapacity);

                pickups[house - offset - 1] -= pickup;
                pickupCount += pickup;
                pickupCapacity -= pickup;

                if (pickupCapacity == 0) {
                    break;
                }
            }
        }

        return answer * 2;
    }
}