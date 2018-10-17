package after;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class Customer {

    @NonNull private final String name;
    @NonNull private final String email;

    public byte[] toBytes() {
        return new byte[0];
    }
}
