package mx.edu.utez.control;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/peliculas/categorias")
public class ControlPeliculas {

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    @Secured("ROLE_RECE")
//    public String index(){
//        return "peliculas";
//    }

    @RequestMapping(value = "/kids", method = RequestMethod.GET)
    @Secured({"ROLE_KID", "ROLE_ADULTO", "ROLE_ADOL"})
    public String kids(){
        return "peliculas de niños";
    }

    @RequestMapping(value = "/adulto", method = RequestMethod.GET)
    @Secured("ROLE_ADULTO")
    public String adulto(){
        return "peliculas de adulto";
    }

    @RequestMapping(value = "/adolecente", method = RequestMethod.GET)
    @Secured({"ROLE_ADOL", "ROLE_ADULTO"})
    public String adolecente(){
        return "peliculas de adolecentes";
    }

    @RequestMapping(value = "/r", method = RequestMethod.GET)
    @Secured({"ROLE_ADULTO"})
    public String R(){
        return "peliculas de clase R";
    }

}
