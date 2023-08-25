import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Shark {
	int r;
	int c;
	int s; // 속도
	int d; // 위 아래 오른쪽 왼 1, 2, 3, 4
	int z; // 크기

	public Shark(int r, int c, int s, int d, int z) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

class Sharks {
	private ArrayList<Shark> list;

	public Sharks() {
		list = new ArrayList<>();
	}

	public void add(Shark s) {
		list.add(s);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void remove(Shark s) {
		list.remove(s);
	}

	public ArrayList<Shark> max() {
		if (list.isEmpty())
			return list;
		ArrayList<Shark> ret = new ArrayList<>();
		list.sort((Shark s1, Shark s2) -> {
			return s2.z - s1.z;
		});
		Shark maxS = list.get(0);
		list.remove(0);
		for (Shark s : list)
			ret.add(s);
		list.clear();
		list.add(maxS);
		return ret;
	}

	public Shark getShark() {
		return list.get(0);
	}

	public void initial() {
		list.clear();
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int R, C, M;
	static ArrayList<Shark> sharks = new ArrayList<>();
	static Sharks[][] maps;

	/** initialize */
	public static void initial() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new Sharks[R + 1][C + 1];
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				maps[i][j] = new Sharks();
			}
		}
		int r, c;
		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			Shark s = new Shark(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			sharks.add(s);
			maps[r][c].add(s);
		}
	}

	/** sharks all move one second */
	public static void oneSecond() {
		for (Shark s : sharks) {
			maps[s.r][s.c].remove(s);
			move(s);
			maps[s.r][s.c].add(s);
		}
		ArrayList<Shark> l;
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				l = maps[i][j].max();
				for (Shark s : l) {
					sharks.remove(s);
				}
			}
		}
	}

	// r, c 속도, 방향
	public static void move(Shark s) {
		int turn;
		if (s.d == 4) {
			turn = s.s % (2 * (C - 1));
			if (turn <= s.c - 1) {
				s.c = s.c - turn;
			} else if (turn <= C + s.c - 2) {
				s.c = 1 + turn - (s.c - 1);
				s.d = 3;
			} else {
				s.c = 2 * C - turn + s.c - 2;
			}
		}

		// 2, 3 작업중
		else if (s.d == 3) {
			turn = s.s % (2 * (C - 1));
			if (turn <= C - s.c)
				s.c = s.c + turn;
			else if (turn <= 2 * C - s.c - 1) {
				s.c = 2 * C - s.c - turn;
				s.d = 4;
			} else
				s.c = s.c + turn - 2 * C + 2;
		}

		else if (s.d == 1) {
			turn = s.s % (2 * (R - 1));
			if (turn <= s.r - 1) {
				s.r = s.r - turn;
			} else if (turn <= R + s.r - 2) {
				s.r = 1 + turn - (s.r - 1);
				s.d = 2;
			} else {
				s.r = 2 * R - turn + s.r - 2;
			}
		}

		else if (s.d == 2) {
			turn = s.s % (2 * (R - 1));
			if (turn <= R - s.r)
				s.r = s.r + turn;
			else if (turn <= 2 * R - s.r - 1) {
				s.r = 2 * R - s.r - turn;
				s.d = 1;
			} else
				s.r = s.r + turn - 2 * R + 2;
		}
	}

	public static int getShark(int c) {
		int r = 1;
		while (r <= R && maps[r][c].isEmpty()) {
			r++;
		}
		if (r == R + 1)
			return 0;
		else {
			Shark ret = maps[r][c].getShark();
			maps[r][c].initial();
			sharks.remove(ret);
			return ret.z;
		}
	}

	public static void printMap() {
		for (int i = 1; i < R + 1; i++) {

			for (int j = 1; j < C + 1; j++) {
				System.out.printf("%d  ", maps[i][j].isEmpty() ? 0 : maps[i][j].getShark().z);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		initial();
		int a = 0;
		for (int i = 1; i <= C; i++) {

			a += getShark(i);
			oneSecond();

		}
		System.out.println(a);
	}
}
