package communication;

public interface IOutCommunicaitonFacade {


	void SendInventoryEmail();
	void SendAbortEmail();
	void SendMaintenenceEmail();
	void SendPowerLossEmail();
	void SendStopEmail();

}
