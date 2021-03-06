package huins.ex.core.MAVLink;

import huins.ex.com.MAVLink.Messages.ApmModes;
import huins.ex.com.MAVLink.common.msg_mission_item;
import huins.ex.com.MAVLink.common.msg_set_mode;
import huins.ex.com.MAVLink.common.msg_set_position_target_global_int;
import huins.ex.com.MAVLink.enums.MAV_CMD;
import huins.ex.com.MAVLink.enums.MAV_FRAME;
import huins.ex.core.model.Flight;

public class MavLinkModes {

    private static final int MAVLINK_SET_POS_TYPE_MASK_POS_IGNORE = ((1 << 0) | (1 << 1) | (1 << 2));
    private static final int MAVLINK_SET_POS_TYPE_MASK_VEL_IGNORE = ((1 << 3) | (1 << 4) | (1 << 5));
    private static final int MAVLINK_SET_POS_TYPE_MASK_ACC_IGNORE = ((1 << 6) | (1 << 7) | (1 << 8));

    public static void setGuidedMode(Flight flight, double latitude, double longitude, double d) {
        msg_mission_item msg = new msg_mission_item();
        msg.seq = 0;
        msg.current = 2; // TODO use guided mode enum
        msg.frame = MAV_FRAME.MAV_FRAME_GLOBAL;
        msg.command = MAV_CMD.MAV_CMD_NAV_WAYPOINT; //
        msg.param1 = 0; // TODO use correct parameter
        msg.param2 = 0; // TODO use correct parameter
        msg.param3 = 0; // TODO use correct parameter
        msg.param4 = 0; // TODO use correct parameter
        msg.x = (float) latitude;
        msg.y = (float) longitude;
        msg.z = (float) d;
        msg.autocontinue = 1; // TODO use correct parameter
        msg.target_system = flight.getSysid();
        msg.target_component = flight.getCompid();
        flight.getMavClient().sendMavPacket(msg.pack());
    }

    public static void sendGuidedPosition(Flight flight, double latitude, double longitude, double altitude){
        msg_set_position_target_global_int msg = new msg_set_position_target_global_int();
        msg.type_mask = MAVLINK_SET_POS_TYPE_MASK_ACC_IGNORE | MAVLINK_SET_POS_TYPE_MASK_VEL_IGNORE;
        msg.coordinate_frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT_INT;
        msg.lat_int = (int) (latitude * 1E7);
        msg.lon_int = (int) (longitude * 1E7);
        msg.alt = (float) altitude;
        msg.target_system = flight.getSysid();
        msg.target_component = flight.getCompid();
        flight.getMavClient().sendMavPacket(msg.pack());
    }

    public static void sendGuidedVelocity(Flight flight, double xVel, double yVel, double zVel){
        msg_set_position_target_global_int msg = new msg_set_position_target_global_int();
        msg.type_mask = MAVLINK_SET_POS_TYPE_MASK_ACC_IGNORE | MAVLINK_SET_POS_TYPE_MASK_POS_IGNORE;
        msg.coordinate_frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT_INT;
        msg.vx = (float) xVel;
        msg.vy = (float) yVel;
        msg.vz = (float) zVel;
        msg.target_system = flight.getSysid();
        msg.target_component = flight.getCompid();
        flight.getMavClient().sendMavPacket(msg.pack());
    }

    public static void sendGuidedPositionAndVelocity(Flight flight, double latitude, double longitude, double altitude,
                                                     double xVel, double yVel, double zVel){
        msg_set_position_target_global_int msg = new msg_set_position_target_global_int();
        msg.type_mask = MAVLINK_SET_POS_TYPE_MASK_ACC_IGNORE;
        msg.coordinate_frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT_INT;
        msg.lat_int = (int) (latitude * 1E7);
        msg.lon_int = (int) (longitude * 1E7);
        msg.alt = (float) altitude;
        msg.vx = (float) xVel;
        msg.vy = (float) yVel;
        msg.vz = (float) zVel;
        msg.target_system = flight.getSysid();
        msg.target_component = flight.getCompid();
        flight.getMavClient().sendMavPacket(msg.pack());
    }

    public static void changeFlightMode(Flight flight, ApmModes mode) {
        msg_set_mode msg = new msg_set_mode();
        msg.target_system = flight.getSysid();
        msg.base_mode = 1; // TODO use meaningful constant
        msg.custom_mode = mode.getNumber();
        flight.getMavClient().sendMavPacket(msg.pack());
    }
}


