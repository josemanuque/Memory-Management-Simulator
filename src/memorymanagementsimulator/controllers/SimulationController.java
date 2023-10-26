package memorymanagementsimulator.controllers;

import memorymanagementsimulator.backend.MMU;
import memorymanagementsimulator.frontend.*;

public class SimulationController {

	//private WelcomeScreen welcomeScreen;
	private SimulationWindow simulationWindow;
	private MMU mmuOpt;
	private MMU mmuUser;
	private Thread optThread;
	private Thread userThread;
	private boolean isRunning = false;

	public SimulationController(){
		//mmuOpt = new MMU("opt");
		mmuUser = new MMU("fifo");
	}

	public void startSimulation(String filename) {
		this.isRunning = true;
		//optThread = new Thread(() -> mmuOpt.startSimulation(""));
		userThread = new Thread(() -> mmuUser.startSimulation(filename));

		//optThread.start();
		userThread.start();

		try {
			userThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*try {
			optThread.join();
			userThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
}