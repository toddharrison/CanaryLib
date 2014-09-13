package net.canarymod.plugin.dependencies;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Basic plugin dependency graph. Can look up forward and reverse dependencies.
 *
 * @author Pwootage
 */
public class DependencyGraph {
    private Map<String, Set<String>> forwardDependencies;
    private Map<String, Set<String>> reverseDependencies;
    private Object lock = new Object();

    public DependencyGraph() {
        forwardDependencies = new LinkedHashMap<String, Set<String>>();
        reverseDependencies = new LinkedHashMap<String, Set<String>>();
        lock = new ReentrantLock();
    }

    public void addDependencies(String from, String[] to) {
        for (String s : to) {
            addEdge(from, s);
        }
    }

    public void addEdge(String from, String to) {
        synchronized (lock) {
            //Add to forward dependencies
            Set<String> fwd = forwardDependencies.get(from);
            if (fwd == null) {
                fwd = new HashSet<String>();
                forwardDependencies.put(from, fwd);
            }
            fwd.add(to);
            //Add to reverse dependencies
            Set<String> rev = reverseDependencies.get(to);
            if (rev == null) {
                rev = new HashSet<String>();
                reverseDependencies.put(to, rev);
            }
            rev.add(from);
        }
    }

    public void removeNode(String node) {
        synchronized (lock) {
            //Remove from forward dependencies
            Set<String> fwd = forwardDependencies.remove(node);
            if (fwd == null) {
                return;
            }
            //Remove from reverse dependencies
            for (String s : fwd) {
                Set<String> rev = reverseDependencies.get(s);
                if (rev != null) {
                    rev.remove(node);
                }
            }
        }
    }

    public Set<String> getDependencies(String node) {
        Set<String> fwd = forwardDependencies.get(node);
        if (fwd == null) {
            return Collections.unmodifiableSet(new HashSet<String>());
        } else {
            return Collections.unmodifiableSet(fwd);
        }
    }

    public Set<String> getDependants(String node) {
        Set<String> rev = reverseDependencies.get(node);
        if (rev == null) {
            return Collections.unmodifiableSet(new HashSet<String>());
        } else {
            return Collections.unmodifiableSet(rev);
        }
    }

}
