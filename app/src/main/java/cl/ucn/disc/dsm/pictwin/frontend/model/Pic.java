package cl.ucn.disc.dsm.pictwin.frontend.model;



import lombok.*;
import androidx.annotation.NonNull;

import java.time.ZonedDateTime;

/**
 * The picture image.
 *
 * @author Matias Canales Benavides.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor// Replace the constructor
public final class Pic {

    /**
     * The Id.
     */

    @Getter
    @Setter
    private Long id;

    /**
     * The instant when the Pic was saved.
     */
    @Getter
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();

    /**
     * The amount of dislikes that a pic has at the time.
     */
    @Getter
    @Builder.Default
    private Integer dislikes = 0;

    /**
     * The corresponding latitude of the user's location.
     */
    @Getter
    private Double latitude;

    /**
     * The corresponding longitude of the user's location.
     */
    @Getter
    private Double longitude;

    /**
     * The corresponding error regarding the user localization's coordinates.
     */
    @Getter
    private Double error;

    /**
     * The amount of views a pic has accumulated over the time.
     */
    @Getter
    @Builder.Default
    private Integer views = 0;

    /**
     * A corresponding title to the pic.
     */
    @Getter
    @Setter
    private String name;

    /**
     * The photo associated to the pic.
     */
    @Getter
    private byte[] picture;

    /**
     * The Owner.
     */
    @Getter
    @Setter
    private User owner;

    /**
     * Increment in one the dislikes.
     *
     * @return the dislikes number.
     */
    public Integer incrementDislikes() {
        this.dislikes++;
        return this.dislikes;
    }

    /**
     * Increment in one the views.
     *
     * @return the views number.l
     */
    public Integer incrementViews() {
        this.views++;
        return this.views;
    }


}


