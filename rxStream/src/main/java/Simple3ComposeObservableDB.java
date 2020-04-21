import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simple3ComposeObservableDB {
    private static class User {
        public String name;
        public Integer id;

        public User(Integer id, String name) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    private static class UserRole {
        public Integer id;
        public String roleName;

        public UserRole(Integer id, String roleName) {
            this.id = id;
            this.roleName = roleName;
        }

        @Override
        public String toString() {
            return "UserRole{" +
                    "id=" + id +
                    ", roleName='" + roleName + '\'' +
                    '}';
        }
    }

    private static class UserDAO {
        private Map<Integer, User> fakeDB = ImmutableMap.of(
            1, new User(1, "A"),
            2, new User(2, "B")
        );

        public Optional<User> find(int id) {
            return Optional.ofNullable(fakeDB.getOrDefault(id, null));
        }
    }

    private static class UserRoleDAO {
        private Map<Integer, List<UserRole>> fakeDB = ImmutableMap.of(
            1, ImmutableList.of(new UserRole(1, "ROLE_1"), new UserRole(1, "ROLE_2")),
            2, ImmutableList.of(new UserRole(2, "ROLE_1"), new UserRole(2, "ROLE_3"))
        );

        public Optional<List<UserRole>> findAll(int id) {
            return Optional.ofNullable(fakeDB.getOrDefault(id, null));
        }
    }


    /**
     * RXJava is synchronous by default. this code shows proof of synchronous execution with RXJava
     */
    private static void startSync() {
        UserDAO userDAO = new UserDAO();
        UserRoleDAO userRoleDAO = new UserRoleDAO();

        // this will only print 2 User
        Observable<Integer> observableUsers = Observable
                .fromIterable(IntStream.rangeClosed(0, 4).boxed().collect(Collectors.toList()));
        observableUsers
                .map(userDAO::find)
                .subscribe(
                        u -> u.map(x -> {
                            System.out.println(String.format("[userDAO] threadId: %s, object: %s", Thread.currentThread().getId(), x));
                            return x;
                        }),
                        t -> t.printStackTrace());
        observableUsers
                .map(userRoleDAO::findAll)
                .subscribe(userRole -> {
                    userRole.map(ur -> {
                        System.out.println(String.format("[userRoleDAO] threadId: %s, object: %s", Thread.currentThread().getId(), ur));
                        return ur;
                    });
                });
    }

    private static void startAsync() {
        UserDAO userDAO = new UserDAO();
        UserRoleDAO userRoleDAO = new UserRoleDAO();
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(5));
        // this will only print 2 User
        Observable<Integer> observableUsers = Observable
                .fromIterable(IntStream.rangeClosed(0, 4).boxed().collect(Collectors.toList()));
        observableUsers
            .map(userDAO::find)
            .subscribe(user -> user.map(u -> {
                System.out.println(String.format("[userDAO] threadId: %s, object: %s", Thread.currentThread().getId(), user));
                return null;
            }));
        observableUsers
            .map(userRoleDAO::findAll)
            .subscribe(userRole -> userRole.map(u -> {
                return null;
            }));
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        compositeDisposable.add(
//            observableUsers
//                    .observeOn(scheduler)
//                    .map(userDAO::find)
////                    .subscribeOn(scheduler)
//                    .subscribe(user -> user.map(u -> {
//                        System.out.println(String.format("[userDAO] threadId: %s, object: %s", Thread.currentThread().getId(), user));
//                        return null;
//                    })));
//        compositeDisposable.add(observableUsers
//                .observeOn(scheduler)
//                .map(userRoleDAO::findAll)
//                .subscribeOn(scheduler)
//                .subscribe()
//        );

//        compositeDisposable.dispose();
    }

    public static void main(String[] args) {
        startSync();
//        startAsync();
    }



}
