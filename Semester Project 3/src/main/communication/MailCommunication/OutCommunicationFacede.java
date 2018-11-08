package communication.MailCommunication;

import communication.IOutCommunicaitonFacade;

public class OutCommunicationFacede implements IOutCommunicaitonFacade {
	private IOutCommunicaitonFacade outFacade;

	public OutCommunicationFacede(){outFacade = new OutCommunicationFacede();}


	@Override
	public void SendInventoryEmail() {

	}

	@Override
	public void SendAbortEmail() {

	}

	@Override
	public void SendMaintenenceEmail() {

	}

	@Override
	public void SendPowerLossEmail() {

	}

	@Override
	public void SendStopEmail() {

	}
}
