/*
	Copyright (c) 2004-2008, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dijit.form.NumberTextBox"]){
dojo._hasResource["dijit.form.NumberTextBox"]=true;
dojo.provide("dijit.form.NumberTextBox");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dojo.number");
dojo.declare("dijit.form.NumberTextBoxMixin",null,{regExpGen:dojo.number.regexp,editOptions:{pattern:"#.######"},_onFocus:function(){
this._setValueAttr(this.attr("value"),false);
this.inherited(arguments);
},_formatter:dojo.number.format,format:function(_1,_2){
if(typeof _1=="string"){
return _1;
}
if(isNaN(_1)){
return "";
}
if(this.editOptions&&this._focused){
_2=dojo.mixin(dojo.mixin({},this.editOptions),this.constraints);
}
return this._formatter(_1,_2);
},parse:dojo.number.parse,filter:function(_3){
return (_3===null||_3===""||_3===undefined)?NaN:this.inherited(arguments);
},serialize:function(_4,_5){
return (typeof _4!="number"||isNaN(_4))?"":this.inherited(arguments);
},_getValueAttr:function(){
var v=this.inherited(arguments);
if(isNaN(v)&&this.textbox.value!==""){
return undefined;
}
return v;
},value:NaN});
dojo.declare("dijit.form.NumberTextBox",[dijit.form.RangeBoundTextBox,dijit.form.NumberTextBoxMixin],{});
}

dojo.declare("custom.form.PercentTextBox", dijit.form.NumberTextBox, {
    regExpGen: function(/*dijit.form.ValidationTextBox.__Constraints*/constraints){
        var re1='((\\d+)(,)*)+'; //Digits and commas
        var re2='(%?)'; //0 or 1 percent sign
        var re3='('+re1+re2+'$|^'+re2+re1+')'; //Allow either %## or ##% or ##
        return re3;
    },
    format: function(/*Number*/ value, /*dojo.number.__FormatOptions*/ constraints){
        value = value;//*100; //Format decimal to percent equivalent
        var formattedValue = this.inherited(arguments);
                if (YAHOO.lang.isNumber(parseFloat(formattedValue))){
                    if (formattedValue<0) formattedValue = 0;
                    if (formattedValue>100) formattedValue = 100;
                }else{
                    if (formattedValue<0) formattedValue = 0;
                }
        formattedValue = formattedValue+"%"; //Add percent sign to make it pretty
        return formattedValue;
    },
    _getValueAttr: function(){
        // summary:
        //        Hook so attr('value') works.
        //        Returns Number, NaN for '', or undefined for unparsable text
        //console.log(questionInputWidget, questionInputWidget.value, this);
        var vattr = this.attr('displayedValue');
        vattr = vattr.replace(/%/g, ""); //Remove percent sign so we can pass to the normal parser
            var v = this.parse(vattr, this.constraints);
        if(isNaN(v) && this.textbox.value !== ''){ // if text other than ''
            var n = Number(this.textbox.value); // check for exponential notation that parse() rejected (erroneously?)
            // returning undefined prevents user text from beng overwritten when doing _setValueAttr(_getValueAttr())
            return (String(n)===this.textbox.value)? n : undefined; // return exponential Number or undefined for random text
        }else{
            if (typeof v == 'number') {
                v = v;// /100;//Convert to decimal for storage
            }
            return v;
        } // Number or NaN for ''
    },
        focus: function(/*Number*/ value, /*dojo.number.__FormatOptions*/ constraints){
            var vattr = this.attr('displayedValue');
        vattr = vattr.replace(/%/g, ""); //Remove percent sign so we can pass to the normal parser
            var v = this.parse(vattr, this.constraints);
                if (YAHOO.lang.isNumber(parseFloat(v))){
                    this.textbox.value = v;
                    return v;
                }else{
                    this.textbox.value = "";
                    return v;
                }
        }
});