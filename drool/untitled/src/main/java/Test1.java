import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Booking;
import org.evrete.KnowledgeService;
import org.evrete.api.StatelessSession;
import rule.SomeRuleSet;

import java.io.IOException;

public class Test1 {

    public static void main(String[] args) throws    IOException {
        KnowledgeService ks = new KnowledgeService();
        StatelessSession session = ks.newKnowledge("JAVA-CLASS", SomeRuleSet.class).newStatelessSession();

        // this will fire the rule
        session.insert(
            Booking.builder().id(1).build().getId(),
            Booking.builder().id(2).build().getId());
        session.fire();

        session = ks.newKnowledge("JAVA-CLASS", SomeRuleSet.class).newStatelessSession();

        // this will not fire the rule
        session.insert(Booking.builder().id(15).build().getId());
        session.insert(Booking.builder().id(2).build().getId());
        session.fire();


    }

}
