package communication;

public interface IMailCommunicaitonFacade {


	void SendInventoryEmail(String machineName);
	void SendAbortEmail(String machineName);
	void SendMaintenenceEmail(String machineName);
	void SendPowerLossEmail(String machineName);
	void SendStopEmail(String machineName);

}
