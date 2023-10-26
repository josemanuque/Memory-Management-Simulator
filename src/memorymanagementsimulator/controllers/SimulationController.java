package memorymanagementsimulator.controllers;

import memorymanagementsimulator.frontend.*;

public class SimulationController {
	private OptMMUController optMmuController;
	private AlgMMUController algMMUController;

	//private WelcomeScreen welcomeScreen;
	private SimulationWindow simulationWindow;
	private Thread optThread;
	private Thread algThread;
	private boolean isRunning = false;

	public SimulationController(){

	}

	public void startSimulation(String filename) {
		this.isRunning = true;
		//optThread = new Thread(() -> mmuOpt.startSimulation(""));
		algThread = new Thread(() -> algMMUController.startSimulation());

		//optThread.start();
		algThread.start();

		try {
			algThread.join();
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