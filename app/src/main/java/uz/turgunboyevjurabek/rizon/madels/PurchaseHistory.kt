package uz.turgunboyevjurabek.rizon.madels

class PurchaseHistory {
    var sana:String?=null
    var shartnoma_id:String?=null
    var ombor:String?=null
    var turi:String?=null
    var miqdor:String?=null
    var summa:String?=null

    constructor(sana: String?, shartnoma_id: String?) {
        this.sana = sana
        this.shartnoma_id = shartnoma_id
    }
    constructor()
}