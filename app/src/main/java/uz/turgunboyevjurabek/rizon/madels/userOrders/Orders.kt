package uz.turgunboyevjurabek.rizon.madels.userOrders

class Orders {
    // testlash uchunю Buyurtmalar fragmentinig resakleView vi uchun
    var image:Int?=null
    var name:String?=null
    var count:Int?=null
    var sumPrice:Int?=null

    constructor(image: Int?, name: String?, count: Int?, sumPrice: Int?) {
        this.image = image
        this.name = name
        this.count = count
        this.sumPrice = sumPrice
    }
}