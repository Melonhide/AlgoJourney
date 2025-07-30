package interviewPrepare.uber.oaProblem3;

public class FindTheBusiestProcessor {
    //You are given:
    //
    //A list of integers capacities, where capacities[i] represents the maximum number of instructions processor i can handle before requiring a reset.
    //
    //A list of strings instructions, where each instruction is either:
    //
    //"package": One instruction to be processed by the next available processor.
    //
    //"closure X": Permanently disables processor X (0-indexed).
    //
    //Each time a "package" instruction is issued:
    //
    //It is assigned to the current processor (starting from processor 0).
    //
    //If the current processor is closed, or its remaining capacity is 0, skip to the next processor.
    //
    //If all processors are either closed or full, reset all non-closed processors back to their original capacity and start from processor 0 again.
    //
    //Your task is to return the index of the processor that processed the most instructions. If there is a tie, return the one with the smallest index.


}
