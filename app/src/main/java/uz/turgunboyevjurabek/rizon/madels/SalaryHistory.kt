package uz.turgunboyevjurabek.rizon.madels

class SalaryHistory {
    var sana:String?=null
    var ShaxsiyAylanma:Int?=null
    var UstozlikBonusi:Int?=null
    var JamoaviyBonus:Int?=null
    var Tulandi:Int?=null
    var Kupon:Int?=null
    var ShaxsiySotuvBonusi:Int?=null
    var JamoaviyAylanma:Int?=null
    var ExtraBonus:Int?=null
    var Qarz:Int?=null

    constructor(sana: String?, ShaxsiyAylanma: Int?) {
        this.sana = sana
        this.ShaxsiyAylanma = ShaxsiyAylanma
    }

    constructor()
}