import java.io.*;
import java.util.StringTokenizer;

class Shark {
    public int row;
    public int column;
    public int speed;
    public int direction;
    public int size;
    public boolean survival;

    public Shark(int row, int column, int speed, int direction, int size) {
        this.row = row;
        this.column = column;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        this.survival = true;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Shark[] sharks = new Shark[M];

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int r = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken()) - 1;
            int s = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int z = Integer.parseInt(tokenizer.nextToken());

            if (d == 1 || d == 2) {
                s %= R * 2 - 2;
            } else {
                s %= C * 2 - 2;
            }

            sharks[index] = new Shark(r, c, s, d, z);
            changeDirection(sharks[index], C, R);
        }

        int output = 0;

        for (int angler = 0; angler < C; angler++) {
            Shark[][] map = new Shark[R][C];

            for (Shark shark : sharks) {
                if (!shark.survival) {
                    continue;
                }

                if (map[shark.row][shark.column] == null) {
                    map[shark.row][shark.column] = shark;
                } else if (map[shark.row][shark.column].size < shark.size) {
                    map[shark.row][shark.column].survival = false;
                    map[shark.row][shark.column] = shark;
                } else {
                    shark.survival = false;
                }
            }

            for (int depth = 0; depth < R; depth++) {
                if (map[depth][angler] != null) {
                    map[depth][angler].survival = false;
                    output += map[depth][angler].size;

                    break;
                }
            }

            for (Shark shark : sharks) {
                if (!shark.survival) {
                    continue;
                }

                move(shark, C, R);
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static void move(Shark shark, int width, int height) {
        int distance = shark.speed;

        while (distance > 0) {
            switch (shark.direction) {
                case 1:
                    if (distance >= shark.row) {
                        distance -= shark.row;
                        shark.row = 0;
                    } else {
                        shark.row -= distance;
                        distance = 0;
                    }

                    break;

                case 2:
                    if (distance >= height - shark.row - 1) {
                        distance -= height - shark.row - 1;
                        shark.row = height - 1;
                    } else {
                        shark.row += distance;
                        distance = 0;
                    }

                    break;

                case 3:
                    if (distance >= width - shark.column - 1) {
                        distance -= width - shark.column - 1;
                        shark.column = width - 1;
                    } else {
                        shark.column += distance;
                        distance = 0;
                    }

                    break;

                case 4:
                    if (distance >= shark.column) {
                        distance -= shark.column;
                        shark.column = 0;
                    } else {
                        shark.column -= distance;
                        distance = 0;
                    }

                    break;
            }

            changeDirection(shark, width, height);
        }
    }

    public static void changeDirection(Shark shark, int width, int height) {
        if (shark.direction == 1 && shark.row == 0) {
            shark.direction = 2;
        } else if (shark.direction == 2 && shark.row == height - 1) {
            shark.direction = 1;
        } else if (shark.direction == 3 && shark.column == width - 1) {
            shark.direction = 4;
        } else if (shark.direction == 4 && shark.column == 0) {
            shark.direction = 3;
        }
    }
}