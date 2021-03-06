// MESSAGE GIMBAL_REPORT_AXIS_CALIBRATION_STATUS PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* 
    		Reports the calibration status for each gimbal axis (whether the axis requires calibration or not)
    	
*/
public class msg_gimbal_report_axis_calibration_status extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS = 212;
    public static final int MAVLINK_MSG_LENGTH = 3;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS;


     	
    /**
    * Whether or not the yaw axis requires calibration, see GIMBAL_AXIS_CALIBRATION_REQUIRED enumeration
    */
    public byte yaw_requires_calibration;
     	
    /**
    * Whether or not the pitch axis requires calibration, see GIMBAL_AXIS_CALIBRATION_REQUIRED enumeration
    */
    public byte pitch_requires_calibration;
     	
    /**
    * Whether or not the roll axis requires calibration, see GIMBAL_AXIS_CALIBRATION_REQUIRED enumeration
    */
    public byte roll_requires_calibration;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS;
        		packet.payload.putByte(yaw_requires_calibration);
        		packet.payload.putByte(pitch_requires_calibration);
        		packet.payload.putByte(roll_requires_calibration);
        
        return packet;
    }

    /**
    * Decode a gimbal_report_axis_calibration_status message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.yaw_requires_calibration = payload.getByte();
        	    
        this.pitch_requires_calibration = payload.getByte();
        	    
        this.roll_requires_calibration = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_gimbal_report_axis_calibration_status(){
        msgid = MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_gimbal_report_axis_calibration_status(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS;
        unpack(mavLinkPacket.payload);        
    }

          
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_GIMBAL_REPORT_AXIS_CALIBRATION_STATUS -"+" yaw_requires_calibration:"+yaw_requires_calibration+" pitch_requires_calibration:"+pitch_requires_calibration+" roll_requires_calibration:"+roll_requires_calibration+"";
    }
}
        