package emidetail.manager.controls;

import java.io.IOException;

/**
 * Command of view to action data model
 * @author Denis_Vitovtov
 *
 */
public interface Command {
	/**
	 * Command save project
	 */
	String SAVE_PR = "saveproject";
	
	/**
	 * Command load project 
	 */
	String LOAD_PR = "loadproject";
	
	/**
	 * Command calc metis
	 */
	String CALC_M = "calcmetis";
	
	/**
	 * Command calc section parametr
	 */
	String CALC_S = "calcsectionpar";
	
	/**
	 * Command report
	 */
	String REPORT_S = "simple_report";
	
	/**
	 * Command generate hard report 
	 */
	String REPORT_H = "hard_report";
        
        /**
         * Command generate code for unit
         */
        String UNIT_CODE_GEN = "unitcodegenerate";
        
        String TRAY_DESC_GEN = "traydescriptiongenerate";
        
        String STAND_DESC_GEN = "standdescriptiongenerate";
	
	/**
	 * Execute this command
	 * @param helper Request helper
	 * @return Next Next page
	 * @throws IOException
	 */
	public String execute(String command) throws  IOException;
}
