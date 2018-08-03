package emidetail.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class <code>ExtFileFilter</code> extends FileFilter.
 * Adds file filter extension.  
 * @author Denis_Vitovtov
 *
 */
class ExtFileFilter {

    /**
     * Extension of the choose files
     */
    private String ext;
    
    protected String description;
    
    protected List<String> extensions;

    ExtFileFilter() {
        this.ext = "*.emi";
        this.description = "EMI Project file";
        this.extensions = new ArrayList<String>();
        extensions.add(ext);
    }

    public String getExt(File file) {
        if(file != null) {
            String filename = file.getName();
            int i = filename.lastIndexOf('.');
            if(i>0 && i<filename.length()-1) {
                return filename.substring(i+1).toLowerCase();
            };
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
    
    public List<String> getExtensions() {
        return extensions;
    }

}
