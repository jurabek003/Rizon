package uz.turgunboyevjurabek.rizon.madels

class KuponTransfer {
    var oluvchiId:Int?=null
    var fullName:String?=null
    var miqdor:Int?=null

    constructor()
    constructor(oluvchiId: Int?, fullName: String?, miqdor: Int?) {
        this.oluvchiId = oluvchiId
        this.fullName = fullName
        this.miqdor = miqdor
    }
}