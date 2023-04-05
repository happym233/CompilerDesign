entry
          addi r14, r0, topaddr
          % processing line53
          % processing line53
          % processing line53
          % processing line53
          % processing line53
          % processing: tmpVar3 := 1
          addi r1,r0,1
          sw -28(r14),r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing line53
          % processing line53
          % processing: tmpVar6 := 2
          addi r1,r0,2
          sw -40(r14),r1
          % processing line53
          % processing: tmpVar8 := 3
          addi r1,r0,3
          sw -48(r14),r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar5 := tmpVar5 * tmpVar8
          lw r1,-36(r14)
          lw r2,-48(r14)
          mul r1,r1,r2
          sw -36(r14),r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar1 := tmpVar1 + tmpVar5
          lw r1,-20(r14)
          lw r2,-36(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          sw -8(r5), r6
          % processing line54
          % processing read statement
          addi r5, r14, 0
          addi r1, r0, buf
          addi r14, r14, -244
          sw -8(r14), r1
          jl r15,getstr
          jl r15,strint
          addi r6, r13, 0
          subi r14, r14, -244
          sw -4(r5), r6
          % processing line55
          % processing line55
          % processing line55
          % processing line55
          % processing line55
          addi r5, r14, 0
          % processing: tmpVar12 := x
          lw r1,-4(r5)
          sw -64(r5), r1
          % processing: tmpVar11 := tmpVar12
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing line55
          % processing line55
          % processing line55
          addi r5, r14, 0
          % processing: tmpVar16 := y
          lw r1,-8(r5)
          sw -80(r5), r1
          % processing: tmpVar15 := tmpVar16
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing line55
          % processing line55
          % processing: tmpVar19 := 10
          addi r1,r0,10
          sw -92(r14),r1
          % processing: tmpVar18 := tmpVar19
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing: tmpVar14 := tmpVar15
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar14 := tmpVar14 + tmpVar18
          lw r1,-72(r14)
          lw r2,-88(r14)
          add r1,r1,r2
          sw -72(r14),r1
          % processing: tmpVar9 := tmpVar10
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing: tmpVar9 := tmpVar9 gt tmpVar14
          lw r1,-52(r14)
          lw r2,-72(r14)
          cgt r1,r1,r2
          sw -52(r14),r1
          lw r1, -52(r14)
          bz r1, elseStatement1
          % processing line56
          % processing write statement
          % processing line56
          % processing line56
          % processing line56
          % processing line56
          addi r6, r14, 0
          % processing: tmpVar23 := x
          lw r2,-4(r6)
          sw -108(r6), r2
          % processing: tmpVar22 := tmpVar23
          lw r2,-108(r14)
          sw -104(r14), r2
          % processing line56
          % processing line56
          % processing: tmpVar26 := 10
          addi r2,r0,10
          sw -120(r14),r2
          % processing: tmpVar25 := tmpVar26
          lw r2,-120(r14)
          sw -116(r14), r2
          % processing: tmpVar21 := tmpVar22
          lw r2,-104(r14)
          sw -100(r14), r2
          % processing: tmpVar21 := tmpVar21 + tmpVar25
          lw r2,-100(r14)
          lw r3,-116(r14)
          add r2,r2,r3
          sw -100(r14),r2
          % processing: tmpVar20 := tmpVar21
          lw r2,-100(r14)
          sw -96(r14), r2
          lw r2, -96(r14)
          addi r14, r14, -244
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -244
          addi r2, r0, charLineSep
          addi r14, r14, -244
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -244
          j endif1
elseStatement1
          % processing line58
          % processing write statement
          % processing line58
          % processing line58
          % processing line58
          % processing line58
          addi r6, r14, 0
          % processing: tmpVar30 := x
          lw r2,-4(r6)
          sw -136(r6), r2
          % processing: tmpVar29 := tmpVar30
          lw r2,-136(r14)
          sw -132(r14), r2
          % processing line58
          % processing line58
          % processing: tmpVar33 := 1
          addi r2,r0,1
          sw -148(r14),r2
          % processing: tmpVar32 := tmpVar33
          lw r2,-148(r14)
          sw -144(r14), r2
          % processing: tmpVar28 := tmpVar29
          lw r2,-132(r14)
          sw -128(r14), r2
          % processing: tmpVar28 := tmpVar28 + tmpVar32
          lw r2,-128(r14)
          lw r3,-144(r14)
          add r2,r2,r3
          sw -128(r14),r2
          % processing: tmpVar27 := tmpVar28
          lw r2,-128(r14)
          sw -124(r14), r2
          lw r2, -124(r14)
          addi r14, r14, -244
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -244
          addi r2, r0, charLineSep
          addi r14, r14, -244
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -244
endif1    
          % processing line60
          % processing line60
          % processing line60
          % processing line60
          % processing line60
          % processing: tmpVar37 := 0
          addi r1,r0,0
          sw -164(r14),r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-164(r14)
          sw -160(r14), r1
          % processing: tmpVar35 := tmpVar36
          lw r1,-160(r14)
          sw -156(r14), r1
          % processing: tmpVar34 := tmpVar35
          lw r1,-156(r14)
          sw -152(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-152(r5)
          sw -12(r5), r6
          % processing line61
gowhile2  
          % processing line61
          % processing line61
          % processing line61
          % processing line61
          addi r6, r14, 0
          % processing: tmpVar41 := z
          lw r2,-12(r6)
          sw -180(r6), r2
          % processing: tmpVar40 := tmpVar41
          lw r2,-180(r14)
          sw -176(r14), r2
          % processing: tmpVar39 := tmpVar40
          lw r2,-176(r14)
          sw -172(r14), r2
          % processing line61
          % processing line61
          % processing line61
          % processing: tmpVar45 := 10
          addi r2,r0,10
          sw -196(r14),r2
          % processing: tmpVar44 := tmpVar45
          lw r2,-196(r14)
          sw -192(r14), r2
          % processing: tmpVar43 := tmpVar44
          lw r2,-192(r14)
          sw -188(r14), r2
          % processing: tmpVar38 := tmpVar39
          lw r2,-172(r14)
          sw -168(r14), r2
          % processing: tmpVar38 := tmpVar38 leq tmpVar43
          lw r2,-168(r14)
          lw r3,-188(r14)
          cle r2,r2,r3
          sw -168(r14),r2
          lw r1, -168(r14)
          bz r1, endwhile2
          % processing line62
          % processing write statement
          % processing line62
          % processing line62
          % processing line62
          % processing line62
          addi r6, r14, 0
          % processing: tmpVar49 := z
          lw r2,-12(r6)
          sw -212(r6), r2
          % processing: tmpVar48 := tmpVar49
          lw r2,-212(r14)
          sw -208(r14), r2
          % processing: tmpVar47 := tmpVar48
          lw r2,-208(r14)
          sw -204(r14), r2
          % processing: tmpVar46 := tmpVar47
          lw r2,-204(r14)
          sw -200(r14), r2
          lw r2, -200(r14)
          addi r14, r14, -244
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -244
          addi r2, r0, charLineSep
          addi r14, r14, -244
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -244
          % processing line63
          % processing line63
          % processing line63
          % processing line63
          % processing line63
          addi r6, r14, 0
          % processing: tmpVar53 := z
          lw r2,-12(r6)
          sw -228(r6), r2
          % processing: tmpVar52 := tmpVar53
          lw r2,-228(r14)
          sw -224(r14), r2
          % processing line63
          % processing line63
          % processing: tmpVar56 := 1
          addi r2,r0,1
          sw -240(r14),r2
          % processing: tmpVar55 := tmpVar56
          lw r2,-240(r14)
          sw -236(r14), r2
          % processing: tmpVar51 := tmpVar52
          lw r2,-224(r14)
          sw -220(r14), r2
          % processing: tmpVar51 := tmpVar51 + tmpVar55
          lw r2,-220(r14)
          lw r3,-236(r14)
          add r2,r2,r3
          sw -220(r14),r2
          % processing: tmpVar50 := tmpVar51
          lw r2,-220(r14)
          sw -216(r14), r2
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-216(r6)
          sw -12(r6), r7
          j gowhile2
endwhile2 
hlt

floatAdd
          sw -4(r14), r1
          sw -8(r14), r2
          sw -12(r14), r3
          sw -16(r14), r4
gowhileFAdd
          cne r5, r2, r4
          bz r5, endwhileFAdd
          clt r6, r2, r4
          bz r6, elseFAdd
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFAdd
elseFAdd
          muli r1, r1, 10
          subi r2, r2, 1
endIfFAdd
          j gowhileFAdd
endwhileFAdd
          add  r1, r1, r3
          sw   -4(r14), r1
          sw   -8(r14), r2
          jr   r15
floatMul
          sw -4(r14), r1
          sw -8(r14), r2
          sw -12(r14), r3
          sw -16(r14), r4
          mul r1, r1, r3
          add r2, r2, r4
          sw  -4(r14), r1
          sw  -8(r14), r2
          jr   r15
floatLeq
          sw -4(r14), r1
          sw -8(r14), r2
          sw -12(r14), r3
          sw -16(r14), r4
gowhileFLeq
          cne r5, r2, r4
          bz   r5, endwhileFLeq
          clt r6, r2, r4
          bz r6, elseFLeq
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFLeq
elseFLeq
          muli r1, r1, 10
          subi r2, r2, 1
          endIfFLeq
          j gowhileFLeq
endwhileFLeq 
          cle r1, r1, r3
          sw -4(r14), r1
          jr r15

buf       res 20
charE     db "e", 0
charLineSep    db "  ", 0
