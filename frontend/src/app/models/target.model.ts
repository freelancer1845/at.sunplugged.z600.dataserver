export class Target {
    id: number;
    createdOn: Date;
    active: boolean;

    name: string;
    createdBy: string;
    lotId: string;
    cuPer: number;
    inPer: number;
    gaPer: number;
    crPer: number;
    work: number;


    constructor(target?: Target) {
        if (target != undefined) {
            this.id = target.id || 0;
            this.createdOn = target.createdOn || new Date();
            this.active = target.active || false;
            this.name = target.name || "";
            this.createdBy = target.createdBy || "";
            this.lotId = target.lotId || "";
            this.cuPer = target.cuPer || 0.0;
            this.inPer = target.inPer || 0.0;;
            this.gaPer = target.gaPer || 0.0;;
            this.crPer = target.crPer || 0.0;;
            this.work = target.work || 0.0;;
        }

    }
}