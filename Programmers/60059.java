class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int lockGrooveNumber = 0;

        for (int row = 0; row < lock.length; row++) {
            for (int col = 0; col < lock[row].length; col++) {
                lockGrooveNumber += 1 - lock[row][col];
            }
        }

        if (lockGrooveNumber == 0) {
            return true;
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotateKey(key);

            for (int rowOffset = -key.length + 1; rowOffset < lock.length; rowOffset++) {
                offsetLoop:
                for (int colOffset = -key[0].length + 1; colOffset < lock[0].length; colOffset++) {
                    int remainingLockGrooveNumber = lockGrooveNumber;

                    for (int keyRow = 0; keyRow < key.length; keyRow++) {
                        for (int keyCol = 0; keyCol < key[keyRow].length; keyCol++) {
                            int lockRow = keyRow + rowOffset;
                            int lockCol = keyCol + colOffset;

                            if (lockRow >= 0 && lockRow < lock.length && lockCol >= 0 && lockCol < lock[0].length) {
                                if (lock[lockRow][lockCol] == key[keyRow][keyCol]) {
                                    continue offsetLoop;
                                }

                                if (lock[lockRow][lockCol] == 0 && key[keyRow][keyCol] == 1) {
                                    remainingLockGrooveNumber--;
                                }
                            }
                        }
                    }

                    if (remainingLockGrooveNumber == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int[][] rotateKey(int[][] key) {
        int[][] rotatedKey = new int[key.length][key[0].length];

        for (int row = 0; row < key.length; row++) {
            for (int col = 0; col < key[row].length; col++) {
                rotatedKey[row][col] = key[key[row].length - col - 1][row];
            }
        }

        return rotatedKey;
    }
}