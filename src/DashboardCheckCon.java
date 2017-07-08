
public class DashboardCheckCon implements Runnable {

	@Override
	public void run() {
		Windows.checkConnection();
	}
}
