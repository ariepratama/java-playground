package rule;

import models.Booking;
import org.evrete.api.RhsContext;
import org.evrete.dsl.annotation.Fact;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.Where;

public class SomeRuleSet {

    public SomeRuleSet() {
        System.out.println("I'm created!!");
    }

    @Rule
    @Where("$booking > $booking1")
    public static void rule(RhsContext ctx, @Fact("$booking") long booking, @Fact("$booking1") long booking1) {
        System.out.println(String.format("got the rule! %s %s", booking, booking1));
    }

}