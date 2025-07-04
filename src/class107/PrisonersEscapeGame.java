package class107;

// 囚徒生存问题
// 有100个犯人被关在监狱，犯人编号0~99，监狱长准备了100个盒子，盒子编号0~99
// 这100个盒子排成一排，放在一个房间里面，盒子编号从左往右有序排列
// 最开始时，每个犯人的编号放在每个盒子里，两种编号一一对应，监狱长构思了一个处决犯人的计划
// 监狱长打开了很多盒子，并交换了盒子里犯人的编号
// 交换行为完全随机，但依然保持每个盒子都有一个犯人编号
// 监狱长规定，每个犯人单独进入房间，可以打开50个盒子，寻找自己的编号
// 该犯人全程无法和其他犯人进行任何交流，并且不能交换盒子中的编号，只能打开查看
// 寻找过程结束后把所有盒子关上，走出房间，然后下一个犯人再进入房间，重复上述过程
// 监狱长规定，每个犯人在尝试50次的过程中，都需要找到自己的编号
// 如果有任何一个犯人没有做到这一点，100个犯人全部处决
// 所有犯人在一起交谈的时机只能发生在游戏开始之前，游戏一旦开始直到最后一个人结束都无法交流
// 请尽量制定一个让所有犯人存活概率最大的策略
// 来自论文<The Cell Probe Complexity of Succinct Data Structures>
// 作者Anna Gal和Peter Bro Miltersen写于2007年
// 如今该题变成了流行题，还有大量科普视频
public class PrisonersEscapeGame {
}
