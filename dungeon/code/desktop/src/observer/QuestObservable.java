package observer;

import quest.Quest;

public interface QuestObservable {
    /**
     * register an observer
     * */
    public void register(Quest quest);

    /**
     * unregister an observer
     * */
    public void unregister(Quest quest);

    /**
     * notify Observer we have registered
     * */
    public void notifyObserver();
}
