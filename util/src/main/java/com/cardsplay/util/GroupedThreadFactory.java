package com.cardsplay.util;



import org.apache.commons.lang3.concurrent.ConcurrentUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Thread factory for creating threads that belong to the specified thread group.
 */
public final class GroupedThreadFactory implements ThreadFactory {

    public static final String DELIMITER = "/";

    private final ThreadGroup group;

    // Cache of created thread factories.
    private static final ConcurrentHashMap<String, GroupedThreadFactory> FACTORIES =
            new ConcurrentHashMap<>();

    /**
     * Returns thread factory for producing threads associated with the specified
     * group name. The group name-space is hierarchical, based on slash-delimited
     * name segments, e.g. {@code onos/intent}.
     *
     * @param groupName group name
     * @return thread factory
     */
    public static GroupedThreadFactory groupedThreadFactory(String groupName) {
        GroupedThreadFactory factory = FACTORIES.get(groupName);
        if (factory != null) {
            return factory;
        }

        // Find the parent group or root the group hierarchy under default group.
        int i = groupName.lastIndexOf(DELIMITER);
        if (i > 0) {
            String name = groupName.substring(0, i);
            ThreadGroup parentGroup = groupedThreadFactory(name).threadGroup();
            factory = new GroupedThreadFactory(new ThreadGroup(parentGroup, groupName));
        } else {
            factory = new GroupedThreadFactory(new ThreadGroup(groupName));
        }

        return ConcurrentUtils.putIfAbsent(FACTORIES, groupName, factory);
    }

    // Creates a new thread group
    private GroupedThreadFactory(ThreadGroup group) {
        this.group = group;
    }

    /**
     * Returns the thread group associated with the factory.
     *
     * @return thread group
     */
    public ThreadGroup threadGroup() {
        return group;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(group, r);
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("group", group).toString();
    }
}
