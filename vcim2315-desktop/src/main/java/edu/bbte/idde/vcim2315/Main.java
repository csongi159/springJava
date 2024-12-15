package edu.bbte.idde.vcim2315;

import edu.bbte.idde.vcim2315.presentation.ComponentUI;
import edu.bbte.idde.vcim2315.service.ComponentService;

public class Main {
    public static void main(String[] args) {
        ComponentService service = new ComponentService();
        new ComponentUI(service);
    }
}
