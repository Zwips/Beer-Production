package communication;

public interface IMailCommunicationFacade {


	void SendInventoryEmail(String machineName);
	void SendAbortEmail(String machineName);
	void SendMaintenanceEmail(String machineName);
	void SendPowerLossEmail(String machineName);
	void SendStopEmail(String machineName);

}
