package cl.ucn.disc.dsm.pictwin.frontend.model;

import androidx.annotation.NonNull;
import lombok.*;

/**
 *  The Twin of Pic.
 *
 * @author Matias Canales Benavides.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Twin {
    /**
     * The Id.
     */
    @Getter
    @Setter
    private Long id;

    /**
     * The dislike
     */
    @Getter
    @Setter
    @Builder.Default
    private Boolean dislike = Boolean.FALSE;

    /**
     * My Pic
     */
    @Getter
    @Setter
    private Pic my;

    /**
     * Your Pic
     */
    @Getter
    @Setter
    private Pic yours;

    /**
     * The Owner
     */
    @Getter
    private User owner;



}
