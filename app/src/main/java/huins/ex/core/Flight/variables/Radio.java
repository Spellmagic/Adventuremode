package huins.ex.core.Flight.variables;

import huins.ex.core.Flight.FlightInterfaces;
import huins.ex.core.Flight.FlightInterfaces.FlightEventsType;
import huins.ex.core.Flight.FlightVariable;
import huins.ex.core.helpers.math.MathUtil;
import huins.ex.core.model.Flight;

/**
 * Parses the mavlink radio messages.
 *
 * TODO: update signal info calculations based on the used radio hardware.
 * TODO: create accessors for the raw signal values, and the updated ones based on the radio hardware. Maybe push the
 * accessors to the client library layer.
 */
public class Radio extends FlightVariable implements FlightInterfaces.OnFlightListener{
	public static final int MAX_FADE_MARGIN = 50;
	public static final int MIN_FADE_MARGIN = 6;

	private double previousSignalStrength = 100;
	
	private boolean isValid = false;

	private int rxerrors = -1;
	private int fixed = -1;
	private int txbuf = -1;
	private double rssi = -1;
	private double remrssi = -1;
	private double noise = -1;
	private double remnoise = -1;

	public Radio(Flight mflight) {
		super(mflight);
        mflight.addFlightListener(this);
	}

	public int getRxErrors() {
		return rxerrors;
	}

	public int getFixed() {
		return fixed;
	}

	public double getRssi() {
		return rssi;
	}

	public double getRemRssi() {
		return remrssi;
	}

	public int getTxBuf() {
		return txbuf;
	}

	public double getNoise() {
		return noise;
	}

	public double getRemNoise() {
		return remnoise;
	}

	public double getFadeMargin() {
		return rssi - noise;
	}

	public double getRemFadeMargin() {
		return remrssi - remnoise;
	}

	/**
	 * Signal Strength in percentage
	 * 
	 * @return percentage
	 */
	public int getSignalStrength() {
		return (int) (MathUtil.Normalize(Math.min(getFadeMargin(), getRemFadeMargin()),
				MIN_FADE_MARGIN, MAX_FADE_MARGIN) * 100);
	}

	public void setRadioState(short rxerrors, short fixed, byte rssi, byte remrssi, byte txbuf,
			byte noise, byte remnoise) {
		isValid = true;
		if (this.rxerrors != rxerrors || this.fixed != fixed || this.rssi != rssi
				|| this.remrssi != remrssi || this.txbuf != txbuf || this.noise != noise
				|| this.remnoise != remnoise) {

			this.rxerrors = rxerrors & 0xFFFF;
			this.fixed = fixed & 0xFFFF;
			this.rssi = SikValueToDB(rssi & 0xFF);
			this.remrssi = SikValueToDB(remrssi & 0xFF);
			this.noise = SikValueToDB(noise & 0xFF);
			this.remnoise = SikValueToDB(remnoise & 0xFF);
			this.txbuf = txbuf & 0xFF;

			int currentSignalStrength = getSignalStrength();
			// if signal strength dips below 10%
			if (currentSignalStrength < 10.0 && previousSignalStrength >= 10.0) {
				mflight.notifyFlightEvent(FlightEventsType.WARNING_SIGNAL_WEAK);
			}
			previousSignalStrength = currentSignalStrength;

			mflight.notifyFlightEvent(FlightEventsType.RADIO);
		}

	}

	/**
	 * Scalling done at the Si1000 radio More info can be found at:
	 * http://copter.ardupilot.com/wiki/common-using-the-3dr-radio-for-telemetry-with-apm-and-px4/#Power_levels
	 */
	private double SikValueToDB(int value) {
		return (value / 1.9) - 127;
	}

	public boolean isValid() {
		return isValid;
	}

    @Override
    public void onFlightEvent(FlightEventsType event, Flight flight) {
        switch(event){
            case DISCONNECTED:
                isValid = false;
                break;
        }
    }
}
