package cl.ucn.disc.dsm.pictwin.frontend.model;

import androidx.annotation.NonNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The User of the App.
 *
 * @author Matias Canales Benavides.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class User {

    /**
     * The Id of the user.
     */

    @Getter
    private Long id;

    /**
     * The email used by the user.
     */
    @Getter
    @NonNull
    @Setter
    private String email;

    /**
     * The password that the user inputs.
     */
    @Getter
    @Setter
    private String password;

    /**
     * The strike count of the user.
     */
    @Getter
    private Integer strikes;

    /**
     * The state of the user.
     */

    @Builder.Default
    @Getter
    @Setter
    private State state = State.ACTIVE;

    /**
     *  The Twins of the user.
     */

    @Builder.Default
    @Getter
    private List<Twin> twins = new ArrayList<>();

    /**
     * The function increases the amount of strikes by one, whenever a pic is considered inappropriate.
     *
     * @return the number of strikes.l
     */
    public Integer incrementStrikes() {
        this.strikes++;
        return this.strikes;
    }

    /**
     * Insert a Twin in the list.
     *
     * @param twin to add.
     */
    public void add(final Twin twin){
        this.twins.add(twin);
    }

}

