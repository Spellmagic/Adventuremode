/** 
* Flags in RALLY_POINT message
*/
package huins.ex.com.MAVLink.enums;

public class RALLY_FLAGS {
	public static final int FAVORABLE_WIND = 1; /* Flag set when requiring favorable winds for landing.  | */
	public static final int LAND_IMMEDIATELY = 2; /* Flag set when plane is to immediately descend to break altitude and land without GCS intervention.  Flag not set when plane is to loiter at Rally point until commanded to land. | */
	public static final int RALLY_FLAGS_ENUM_END = 3; /*  | */
}
            