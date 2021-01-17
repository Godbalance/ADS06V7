package com.godbalance.ads06

class FDB {

    var adres: String? = null
    var barcode: String? = null
    var name: String? = null
    var lm: String? = null
    var date: String? = null
    var stock: String? = null
    var pk: String? = null

    constructor(){

    }

    constructor(adres: String?, barcode: String?, name: String?, lm: String?, date: String?, stock: String?, pk: String?) {
        this.adres = adres
        this.barcode = barcode
        this.name = name
        this.lm = lm
        this.date = date
        this.stock = stock
        this.pk = pk
    }

}