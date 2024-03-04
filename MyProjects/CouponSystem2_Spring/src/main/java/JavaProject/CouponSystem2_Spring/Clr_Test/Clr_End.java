package JavaProject.CouponSystem2_Spring.Clr_Test;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(5)
public class Clr_End implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        PrintSystemEndMessage();
        //Todo - uncomment before hand-in? (Ask Zeev)
        //System.exit(0);
    }

    private void PrintSystemEndMessage() {
        System.out.println(
                "                       T-H-E  E-N-D                    \n" +
                "                                                       \n" +
                "                             .''.\n" +
                "       .''.      .        *''*    :_\\/_:     .\n" +
                "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                "  .''.: /\\ :    /)\\   ':'* /\\ *  : '..'.  -=:o:=-\n" +
                " :_\\/_:'.:::.  | ' *''*    * '.\\'/.'_\\(/_ '.':'.'\n" +
                " : /\\ : :::::  =  *_\\/_*     -= o =- /)\\     '  *\n" +
                "  '..'  ':::' === * /\\ *     .'/.\\'.  ' ._____\n" +
                "      *        |   *..*         :       |.   |' .---\"|\n" +
                "        *      |     _           .--'|  ||   | _|    |\n" +
                "        *      |  .-'|       __  |   |  |    ||      |\n" +
                "     .-----.   |  |' |  ||  |  | |   |  |    ||      |\n" +
                " ___'       ' /\"\\ |  '-.\"\".    '-'   '-.'    '`      |____\n" +
                "jgs~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "  &                    ~-~-~-~-~-~-~-~-~-~   /|\n" +
                " ejm97    )      ~-~-~-~-~-~-~-~  /|~       /_|\\\n" +
                "        _-H-__  -~-~-~-~-~-~     /_|\\    -~======-~\n" +
                "~-\\XXXXXXXXXX/~     ~-~-~-~     /__|_\\ ~-~-~-~\n" +
                "~-~-~-~-~-~    ~-~~-~-~-~-~    ========  ~-~-~-~\n" +
                "      ~-~~-~-~-~-~-~-~-~-~-~-~-~ ~-~~-~-~-~-~\n" +
                "                        ~-~~-~-~-~-~" +
                "");
        System.out.println();
        System.out.println();
        System.out.println("###############################################################");
        System.out.println("####### (-:  So long, and thanks for all the fish!  :-) #######");
        System.out.println("###############################################################");
        System.out.println();
        System.out.println();
    }
}
