package socialNetwork.chainOfResponsibility;

import socialNetwork.observer.builder.Profile;

public abstract class ProfileFinder {
	
	private ProfileFinder next;

	public ProfileFinder(ProfileFinder next) {
		this.next = next;
	}
	
	public final boolean matchProfile(Profile toSearch, Profile toCompare) {
		if (doMatch(toSearch, toCompare)) {
			return true;
		} else if (next != null) {
			return next.matchProfile(toSearch, toCompare);
		}
		return false;
	}

	protected abstract boolean doMatch(Profile toSearch, Profile toCompare);
}
