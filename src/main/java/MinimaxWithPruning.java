public class MinimaxWithPruning {
    //the maximum number of levels that the algorithm...
    //generate before calculating the heuristic score for the states
    Heuristic h=new Heuristic();
    public int nodeExpanded=0;

    //return the number of the column from 1 to 7 that the agent should play in
    //turn is true when it's R's turn , else it's Y's turn

    public State minimaxWithPruning(State state , int levels , boolean player,double alpha,double beta){
        if (!player){
            return maximizeWithPruning(state , levels , player,alpha,beta);
        }
        else{
            return minimizeWithPruning(state , levels , player,alpha,beta);
        }
    }
    private State maximizeWithPruning(State state,int levels ,boolean player,double alpha, double beta){
        nodeExpanded++;
        //to check if K is 0 or if we reached a terminal node
        if(levels==0 || state.isFinish()){
            //call the heuristic to set the utility of the state then return it
            state.setHeuristicScore(h.getHeuristicScore(state.toGrid()));
            return state;
        }
        //a state to put the maximum value found
        State maximum=new State(state.toGrid());
        maximum.setHeuristicScore(Integer.MIN_VALUE);
        //loop over the children of the state to update the value of maximum state
        for(State child : state.getAllNeighbours(player)){
            child.setHeuristicScore(minimizeWithPruning(child,levels-1,false,alpha,beta).getHeuristicScore());
            if(child.getHeuristicScore()>maximum.getHeuristicScore()) {
                maximum = new State(child);
                alpha = maximum.getHeuristicScore();

            }
            if (beta <= alpha){
                break;
            }
        }
        //return the state with the maximum value found
        return maximum;
    }

    private State minimizeWithPruning(State state, int levels , boolean player,double alpha, double beta){
        nodeExpanded++;
        //to check if levels is 0 or if we reached a terminal node
        if(levels==0 || state.isFinish()){
            //call the heuristic to set the utility of the state then return it
            state.setHeuristicScore(h.getHeuristicScore(state.toGrid()));
            return state;
        }
        //a state to put the minimum value found
        State minimum=new State(state.toGrid());
        minimum.setHeuristicScore(Integer.MAX_VALUE);
        //loop over the children of the state to update the value of minimum state
        for(State child : state.getAllNeighbours(player)){
            child.setHeuristicScore(maximizeWithPruning(child,levels-1,true,alpha,beta).getHeuristicScore());
            if(child.getHeuristicScore()<minimum.getHeuristicScore()) {
                minimum = new State(child);
                beta = minimum.getHeuristicScore();
            }
            if (beta <= alpha){
                break;
            }
        }
        //return the state with the minimum value found
        return minimum;
    }


}
