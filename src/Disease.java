

/*
 *	This file is part of DiseaseSim version 0.3 -  an agent based modeling research tool	*
 *	Copyright (C) 2012 Marek Laskowski				*
 *											*
 *	This program is free software: you can redistribute it and/or modify		*
 *	it under the terms of the GNU General Public License as published by		*
 *	the Free Software Foundation, either version 3 of the License, or		*
 *	(at your option) any later version.						*
 *											*
 *	This program is distributed in the hope that it will be useful,			*
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of			*
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			*
 *	GNU General Public License for more details.					*
 *											*
 *	You should have received a copy of the GNU General Public License		*
 *	along with this program.  If not, see <http://www.gnu.org/licenses/>.		*
 *											*
 *	email: mareklaskowski@gmail.com							*
 ****************************************************************************************/
/**
 * 
 * a class representing a disease
 * has several states
 * a string representing the strain
 * also maintains the time since infection.
 */
public class Disease {
	
	public enum State{NONE, SUSCEPTIBLE, INFECTED, RECOVERED};
	
	private String strain;
	private State state;
	
	//in seconds
	private double timeSinceInfection;
	
	/**
	 * basic constructor that initializes the diseases strain.
	 * @param s a string representing the new strain
	 */
	public Disease(String s)
	{
		this.strain = s;
		this.state = state.NONE;
		timeSinceInfection = 0;
		
	}
	/**
	 * 
	 * @return the string representing the strain
	 */
	public String getStrain(){
		return strain;
	}
	/**
	 * 
	 * @return the current state of the disease
	 */
	public State getState(){
		return state;
	}
	
	/**
	 * a way to set the disease state
	 * @param newState the new state
	 */
	public void setState(State newState)
	{
		state = newState;
	}
	
	/**
	 * 
	 * @return time since infection occurred in seconds
	 */
	
	public double getTimeSinceInfection()
	{
		return timeSinceInfection;
	}
	
	/**
	 * simulate the agent's behavior for this time period
	 * @param deltaTime time that the simulation has advanced
	 */
	public void tick(double deltaTime)
	{
		timeSinceInfection += deltaTime;
	}
	
	
	
}
