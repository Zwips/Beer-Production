package communication.MailCommunication;

import communication.IMailCommunicaitonFacade;

public class MailCommunicationFacede implements IMailCommunicaitonFacade {


	@Override
	public void SendInventoryEmail(String machineName) {
		new InventoryEmail().SendInventoryEMail(machineName);
	}

	@Override
	public void SendAbortEmail(String machineName) {
		new AbortEmail().SendAbortEmail(machineName);

	}

	@Override
	public void SendMaintenenceEmail(String machineName) {
		new MaintenenceEmail().SendMaintenenceEMail(machineName);
	}

	@Override
	public void SendPowerLossEmail(String machineName) {
		new PowerLossEmail().SendPowerLossEmail(machineName);
	}

	@Override
	public void SendStopEmail(String machineName) {
		new StopEmail().SendStopEMail(machineName);
	}
}
