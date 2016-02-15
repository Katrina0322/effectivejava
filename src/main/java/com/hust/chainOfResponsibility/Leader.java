package com.hust.chainOfResponsibility;

public abstract class Leader {
	private String name;
	protected Leader nextLeader;
	public Leader(String name) {
		super();
		this.name = name;
	}
	public void setNextLeader(Leader nextLeader) {
		this.nextLeader = nextLeader;
	}
	
	public abstract void handlerRequest(LeaveRequest leaveRequest);
	
}
